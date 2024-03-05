# USE CASE: 3 Generate Population Report for Top 4 Countries by Region organized by popuation

## CHARACTERISTIC INFORMATION

### Goal in Context

*As an employee, I require a report detailing all countries within a particular region, ordered by population size from largest to smallest. The report should include the top 4 countries from each region, so that I can analyze population distributions within regions.*

### Scope

Organization's demographic data.

### Level

Primary task.

### Preconditions

1. The user has access to the demographic data.
2. The database contains accurate population data for all countries.

### Success End Condition

A report is generated showing the top 4 countries by population within the specified region, organized from largest to smallest population.

### Failed End Condition

No report is generated or the report is incomplete or inaccurate.

### Primary Actor

Employee.

### Trigger

The employee requests a report on country populations by region.

## MAIN SUCCESS SCENARIO

1. Employee requests a report listing top 10 countries by population within a specific region.
2. System prompts the employee to specify the region.
3. Employee provides the name of the region.

## EXTENSIONS

**Region not found**:
    1. System informs the employee that the specified region does not exist or is not available in the database.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
