# USE CASE: 8 Generate City Population Report for a Specified District

## CHARACTERISTIC INFORMATION

### Goal in Context

As an employee, I require a report listing the top 2 populated cities within a specific district, sorted by population size from largest to smallest. This report will enable me to analyze population distributions within districts.

### Scope

Organization's demographic data.

### Level

Primary task.

### Preconditions

1. The user has access to the demographic data.
2. The database contains accurate population data for all cities.
3. The specified district exists in the database.

### Success End Condition

A report is generated displaying the top 2 populated cities within the specified district, sorted by population size from largest to smallest.

### Failed End Condition

No report is generated, or the report is incomplete or inaccurate.

### Primary Actor

Employee.

### Trigger

The employee requests a report on city populations within a specific district.

## MAIN SUCCESS SCENARIO

1. Employee requests a report listing the top 2 populated cities within a specific district.
2. System prompts the employee to specify the district.
3. Employee provides the name of the district.

## EXTENSIONS

**District not found**:
   1. System informs the employee that the specified district does not exist or is not available in the database.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
