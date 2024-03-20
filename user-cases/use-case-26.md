# USE CASE: 26 Access to retrieve the population of the world

## CHARACTERISTIC INFORMATION.

### Goal in Context

*Retrieve the population of the world*

### Scope

Organization's demographic data.

### Level

Primary task.

### Preconditions

1. The user has access to the organization's database system.
2. The database contains accurate and up-to-date language population data for various languages.

### Success End Condition

A report is generated providing the number of people who speak Chinese, English, Hindi, Spanish, and Arabic, sorted from greatest number to smallest. The report also includes the percentage of the world population for each language.

### Failed End Condition

No report is generated, or the report is incomplete or inaccurate.

### Primary Actor

Employee.

### Trigger

The employee initiates a request to generate a language population report.

## MAIN SUCCESS SCENARIO

1. Employee initiates a request to generate a language population report.
2. System retrieves language population data for Chinese, English, Hindi, Spanish, and Arabic from the database.
3. System calculates the total number of speakers for each language.

## EXTENSIONS

None.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
