# USE CASE: 18 Access Population Data for a Specific Country

## CHARACTERISTIC INFORMATION

### Goal in Context

As an employee, I require access to the population data for a specific country. This will enable me to obtain demographic insights and perform analyses related to the population distribution within the chosen country.

### Scope

Organization's demographic data.

### Level

Primary task.

### Preconditions

1. The user has access to the organization's database system.
2. The database contains accurate and up-to-date population data for various countries.

### Success End Condition

The employee successfully accesses the population data for the specified country from the organization's database.

### Failed End Condition

The employee is unable to access the population data for the specified country due to technical issues or data unavailability.

### Primary Actor

Employee.

### Trigger

The employee initiates a request to access the population data for a specific country.

## MAIN SUCCESS SCENARIO

1. Employee initiates a request to access the population data for a specific country.
2. System prompts the employee to specify the country.
3. Employee provides the name or identifier of the desired country.

## EXTENSIONS

**Country Not Found**:
   1. If the specified country does not exist in the database, the system informs the employee and prompts to enter a valid country.

**Data Unavailable**:
   1. If the population data for the specified country is not available in the database, the system informs the employee about the unavailability and advises to check back later or contact the administrator for further assistance.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
