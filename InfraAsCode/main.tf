provider "aws" {
    access_key = "mock_access_key"
    secret_key = "mock_secret_key"
    region     = "us-east-1"

    skip_credentials_validation = true
    skip_metadata_api_check     = true
    skip_requesting_account_id  = true

    endpoints {
        ec2 = "http://localhost:4566"
    }
}

resource "aws_instance" "ec2_instance" {
    ami           = "ami-df5de72bdb3b" 
    instance_type = "t2.micro"
}   