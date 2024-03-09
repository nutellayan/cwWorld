# USE CASE: 5 Generate Population Report for Top 10 Cities in a Continent organized by population

## CHARACTERISTIC INFORMATION

### Goal in Context

*As an employee, I need a report detailing the cities within a specific continent, organized by population size from largest to smallest**. The report should include the top 10 populated cities in the continent, so that I can analyze population distributions within continents.*

### Scope

Organization's demographic data.

### Level

Primary task.

### Preconditions

1. The user has access to the demographic data.
2. The database contains accurate population data for all cities.
3. The continent specified by the user exists in the database.

### Success End Condition

A report is generated showing the top 10 cities by population within the specified continent, organized from largest to smallest population.

### Failed End Condition

No report is generated or the report is incomplete or inaccurate.

### Primary Actor

Employee.

### Trigger

The employee requests a report on city populations within a specific continent.

## MAIN SUCCESS SCENARIO

1. Employee requests a report listing cities within a specific continent by population size.
2. System prompts the employee to specify the continent.
3. Employee provides the name of the continent.

## EXTENSIONS

**Continent not found**:
    1. System informs the employee that the specified continent does not exist or is not available in the database.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
