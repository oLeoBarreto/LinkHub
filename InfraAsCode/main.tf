# Setting the AWS default values
provider "aws" {
    access_key = "mock_access_key"
    secret_key = "mock_secret_key"
    region     = "us-east-1"

    skip_credentials_validation = true
    skip_metadata_api_check     = true
    skip_requesting_account_id  = true

    endpoints {
        apigateway     = "http://localhost:4566"
        apigatewayv2   = "http://localhost:4566"
        cloudformation = "http://localhost:4566"
        cloudwatch     = "http://localhost:4566"
        dynamodb       = "http://localhost:4566"
        ec2            = "http://localhost:4566"
        es             = "http://localhost:4566"
        elasticache    = "http://localhost:4566"
        firehose       = "http://localhost:4566"
        iam            = "http://localhost:4566"
        kinesis        = "http://localhost:4566"
        lambda         = "http://localhost:4566"
        rds            = "http://localhost:4566"
        redshift       = "http://localhost:4566"
        route53        = "http://localhost:4566"
        s3             = "http://s3.localhost.localstack.cloud:4566"
        secretsmanager = "http://localhost:4566"
        ses            = "http://localhost:4566"
        sns            = "http://localhost:4566"
        sqs            = "http://localhost:4566"
        ssm            = "http://localhost:4566"
        stepfunctions  = "http://localhost:4566"
        sts            = "http://localhost:4566"
    }
}

# Creating a VPC (Virtual Private Cloud)
resource "aws_vpc" "main" {
    cidr_block       = "10.0.0.0/16"
    instance_tenancy = "default"

    tags = {
        Name = "main"
    }
}

# Creating a default security group for the instances
resource "aws_security_group" "default_security_group" {
  name        = "default_security_group"
  description = "The default security group"
  vpc_id      = aws_vpc.main.id

  tags = {
    Name = "DefaultSecurityGroup"
  }
}

# Creating the inbound traffic rules for the postgres db instance
resource "aws_security_group_rule" "ingress_postgres_secgroup_roles" {
  type              = "ingress"
  from_port         = 5432
  to_port           = 5423
  protocol          = "tcp"
  cidr_blocks       = ["10.0.0.0/16"]
  security_group_id = aws_security_group.default_security_group.id
}

# Creating the outbound traffic rules for the postgres db instance
resource "aws_security_group_rule" "egress_postgres_secgroup_roles" {
  type              = "egress"
  from_port         = 0
  to_port           = 0
  protocol          = "-1"
  cidr_blocks       = ["0.0.0.0/0"]
  security_group_id = aws_security_group.default_security_group.id
}

# Creating a Postgres database instance
resource "aws_db_instance" "postgres_db" {
  allocated_storage      = 10
  db_name                = "linkhub_db"
  engine                 = "postgres"
  engine_version         = "17.0"
  instance_class         = "db.t3.micro"
  username               = "admin"
  password               = "admin"
  parameter_group_name   = "default.postgres17"
  skip_final_snapshot    = true
  vpc_security_group_ids = [aws_security_group.default_security_group.id]
}

# Creating a DynamoDB Table 
resource "aws_dynamodb_table" "link-analytics" {
  name = "link-analytics"
  read_capacity = 20
  write_capacity = 20
  hash_key = "ShortCode"
  range_key = "ClickTimestamp"

  attribute {
    name = "ShortCode"
    type = "S"
  }

  attribute {
    name = "ClickTimestamp"
    type = "S"
  }

  tags = {
    Name = "dynamo-linkAnalytics-table"
  }
}

#Creating SQS instance
resource "aws_sqs_queue" "link-clicked-queue" {
  name = "link-clicked-queue.fifo"
  delay_seconds = 90
  max_message_size = 2048
  message_retention_seconds = 86400 # 24 Hours
  receive_wait_time_seconds = 10

  fifo_queue = true # Defining as a fifo queue, first message in is the first message out
}