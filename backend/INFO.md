# Backend Component - PCBuilder Project

Handles server-side logic, database communication, and operations.

### Maintainers
- Luis (@prodanyboy) - Database
- Gerardo (@SONRIXMX) - Server

### Objectives
- Develop server-side logic and operations
- Configure and manage the database
- Implement API endpoints for frontend communication
- Ensure data integrity and security

## Technical Specifications

### Technology Stack
- **Languages**: Java, SQL
- **Frameworks**: None
- **Database**: SQL Server

### Project Scope
- Database configuration and management
- Data validation and security
- Integration with frontend services

### Complexity Assessment
- **Overall Complexity**: High
- **Key Challenges**:
  - Ensuring data security and integrity
  - Optimizing database queries
  - Implementing robust API endpoints
  - Handling concurrent requests

## Integration Points
- Database communication
- API endpoints for frontend
- Authentication and authorization services

## Commit History
- _**2025-02-02 10:45 AM**_ | Refactor backend application to implement logging and socket communication - (@artu)
- _**2025-02-02 03:25 PM**_ | Update INFO.md with recent commit details and enhance Sockets class documentation - (@artu)
- _**2025-02-03 11:30 PM**_ | Refactor logging implementation to include method names and enhance database connection handling - (@artu)
- _**2025-02-04 09:38 PM**_ | Update migrated to Maven build system & improved all helpers and utilities - (@artu)
- _**2025-02-04 09:54 PM**_ | Update configuration handling to create a detailed config file and adjust logger settings - (@artu)
- _**2025-02-05 11:44 AM**_ | Refactor database connection handling to include retry logic and update configuration settings - (@artu)
- _**2025-02-05 10:37 PM**_ | Add Resolver and update Sockets to handle operations; enhance database connection management - (@artu)
- _**2025-02-06 09:15 AM**_ | Refactor operation handling in ComponentOperation and UserOperation; update Resolver to use string-based operation types and enhance Sockets with improved error handling and shutdown logic - (@artu)
- _**2025-02-10 08:37 PM**_ | Add null check for database connection in ComponentOperation and UserOperation - (@artu)
- _**2025-02-13 10:45 AM**_ | Add JWT authentication support and user operations in UserOperation; update pom.xml for dependencies - (@artu)
- _**2025-02-13 11:30 AM**_ | Implement JWT token generation in UserOperation for successful login response - (@artu)
- _**2025-02-13 12:00 PM**_ | Refactor token generation and verification methods in Crypto; add RegenAuthToken and ReplaceRegenToken methods in UserOperation for enhanced JWT handling - (@artu)
- _**2025-02-13 12:30 PM**_ | Update server port and enhance logging in operations for better error handling and operation identification - (@artu)