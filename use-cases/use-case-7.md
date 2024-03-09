# USE CASE: 7 Generate City Population Report for a Specified Country

## CHARACTERISTIC INFORMATION

### Goal in Context

*As an employee, I require a report listing all cities within a particular country, arranged by population size from largest to smallest. The report should display the top 2 populated cities in the country. This will enable me to analyze population distributions within countries.*

### Scope

Organization's demographic data.

### Level

Primary task.

### Preconditions

1. The user has access to the demographic data.
2. The database contains accurate population data for all cities.
3. The specified country exists in the database.

### Success End Condition

A report is generated displaying the cities within the specified country, organized by population size from largest to smallest. The report includes information on the top 2 populated cities in the country.

### Failed End Condition

No report is generated, or the report is incomplete or inaccurate.

### Primary Actor

Employee.

### Trigger

The employee requests a report on city populations within a specific country.

## MAIN SUCCESS SCENARIO

1. Employee requests a report listing cities within a specific country by population size.
2. System prompts the employee to specify the country.
3. Employee provides the name of the country.

## EXTENSIONS

**Country not found**:
   1. System informs the employee that the specified country does not exist or is not available in the database.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
