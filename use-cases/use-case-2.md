# USE CASE: 2 Generate Population Report for Top 10 Countries by Continent organised by their population

## CHARACTERISTIC INFORMATION

### Goal in Context

*As an employee, I require a report that organizes countries within a specific continent, arranging them from largest to smallest population**. The report should include the top 10 countries from each continent, so that I can analyze population distributions within continents.*

### Scope

Organization's demographic data.

### Level

Primary task.

### Preconditions

1. The user has access to the demographic data.
2. The database contains accurate population data for all countries.

### Success End Condition

A report is generated showing the top 10 countries by population within the specified continent, organized from largest to smallest population.

### Failed End Condition

No report is generated or the report is incomplete or inaccurate.

### Primary Actor

Employee.

### Trigger

The employee requests a report on country populations by continent.

## MAIN SUCCESS SCENARIO

1. Employee requests a report listing top 10 countries by population within a specific continent.
2. System prompts the employee to specify the continent.
3. Employee provides the name of the continent.

## EXTENSIONS

**Continent not found**:
    1. System informs the employee that the specified continent does not exist or is not available in the database.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
