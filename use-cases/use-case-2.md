# USE CASE: 2 Generate Country Population in a continent organized by Population

## CHARACTERISTIC INFORMATION

### Goal in Context

*As an employer, I want to generate a report listing all countries in a continent sorted by population.*

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
