CREATE TABLE applicants (
    id UUID PRIMARY KEY,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    name VARCHAR(150) NOT NULL,
    mail VARCHAR(255) UNIQUE
);

CREATE TABLE jobs (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    expiration DATE NOT NULL,
    salary DECIMAL(10,2) NOT NULL
);

CREATE TABLE jobs_applicants (
    job UUID,
    applicant UUID,
    FOREIGN KEY (job) REFERENCES jobs(id),
    FOREIGN KEY (applicant) REFERENCES applicants(id),
    PRIMARY KEY (job, applicant)
);