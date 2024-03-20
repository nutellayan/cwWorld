# USE CASE: 11 Generate City Population Report in a district organised by largest to smallest

## CHARACTERISTIC INFORMATION.

### Goal in Context

*As an employee, I want to generate a report of all cities in a district organized by largest population to smallest.*

### Scope

Organization's demographic data.

### Level

Primary task.

### Preconditions

1. The user has access to the demographic data.
2. The database contains accurate population data for all capital cities.
3. The specified region exists in the database.

### Success End Condition

A report is generated displaying all capital cities within the specified region, sorted by population size from largest to smallest. The report includes information on the top 5 populated capital cities in the region.

### Failed End Condition

No report is generated, or the report is incomplete or inaccurate.

### Primary Actor

Employee.

### Trigger

The employee requests a report on capital cities within a specific region sorted by population size.

## MAIN SUCCESS SCENARIO

1. Employee requests a report listing all capital cities within a specific region sorted by population size.
2. System prompts the employee to specify the region.
3. Employee provides the name of the region.

## EXTENSIONS

**Region not found**:
   1. System informs the employee that the specified region does not exist or is not available in the database.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
