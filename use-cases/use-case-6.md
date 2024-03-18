# USE CASE: 6 Generate Population Report for Top 4 countries in a region organized by population

## CHARACTERISTIC INFORMATION

### Goal in Context

*Generate report: Top 4 populated countries in a region.*

### Scope

Organization's demographic data.

### Level

Primary task.

### Preconditions

1. The user has access to the demographic data.
2. The database contains accurate population data for all cities.
3. The specified region exists in the database.

### Success End Condition

A report is generated displaying the cities within the specified region, organized by population size from largest to smallest. If possible, the report includes information on the top 10 populated cities in the region.

### Failed End Condition

No report is generated, or the report is incomplete or inaccurate.

### Primary Actor

Employee.

### Trigger

The employee requests a report on city populations within a specific region.

## MAIN SUCCESS SCENARIO

1. Employee requests a report listing cities within a specific region by population size.
2. System prompts the employee to specify the region.
3. Employee provides the name of the region.

## EXTENSIONS

**Region not found**:
   1. System informs the employee that the specified region does not exist or is not available in the database.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
