# USE CASE: 19 Report listing all capital cities in a region organized by population size

## CHARACTERISTIC INFORMATION.

### Goal in Context

*As an employee, I want to generate a report listing all capital cities in a region organized by population size.*

### Scope

Organization's demographic data.

### Level

Primary task.

### Preconditions

1. The user has access to the organization's database system.
2. The database contains accurate and up-to-date population data for various districts.

### Success End Condition

The employee successfully accesses the population information for the specified district from the organization's database.

### Failed End Condition

The employee is unable to access the population information for the specified district due to technical issues or data unavailability.

### Primary Actor

Employee.

### Trigger

The employee initiates a request to access the population information for a specific district.

## MAIN SUCCESS SCENARIO

1. Employee initiates a request to access the population information for a specific district.
2. System prompts the employee to specify the district.
3. Employee provides the name or identifier of the desired district.

## EXTENSIONS

**District Not Found**:
   1. If the specified district does not exist in the database, the system informs the employee and prompts to enter a valid district.

**Data Unavailable**:
   1. If the population data for the specified district is not available in the database, the system informs the employee about the unavailability and advises to check back later or contact the administrator for further assistance.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
