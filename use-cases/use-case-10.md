# USE CASE: 10 Generate Report of Capital Cities within a Specified Continent

## CHARACTERISTIC INFORMATION

### Goal in Context

As an employee, I require a report listing all capital cities within a specified continent, organized by population size from largest to smallest. The report should include the top 5 populated capital cities in each continent. This will facilitate analysis of population distributions among capital cities within continents.

### Scope

Organization's demographic data.

### Level

Primary task.

### Preconditions

1. The user has access to the demographic data.
2. The database contains accurate population data for all capital cities.
3. The specified continent exists in the database.

### Success End Condition

A report is generated displaying all capital cities within the specified continent, organized by population size from largest to smallest. The report includes information on the top 5 populated capital cities in the continent.

### Failed End Condition

No report is generated, or the report is incomplete or inaccurate.

### Primary Actor

Employee.

### Trigger

The employee requests a report on capital cities within a specified continent organized by population size.

## MAIN SUCCESS SCENARIO

1. Employee requests a report listing all capital cities within a specified continent organized by population size.
2. System prompts the employee to specify the continent.
3. Employee provides the name of the continent.

## EXTENSIONS

**Continent not found**:
   1. System informs the employee that the specified continent does not exist or is not available in the database.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
