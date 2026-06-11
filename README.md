# WinWin.travel test task

<br />

## Architecture
  auth-api
    - Registration/Login
    - JWT auth
    - Processing endpoint

  data-api 
    - Transformation service
    - Protected with X-Internal-Token

  PostgreSQL
    - users and processing_log tables
    
<br />
    

## Run instructions: 

1. Package each service to <code>.jar</code>

    <code>./auth-api/mvnw -f auth-api/pom.xml clean package</code>
   
    <code>./data-api/mvnw -f data-api/pom.xml clean package</code> 

2. Create <code>.env</code> file and configure next environment variables:
   ```
   POSTGRES_URL=your_db_url
   POSTGRES_USER=your_postgres_username
   POSTGRES_PASSWORD=your_postgres_password
   JWT_SECRET=jwt_secret
   INTERNAL_TOKEN=any_secret
   ```

    Postgres variables such as POSTGRES_URL/USER/PASSWORD are not mandatory. If you won't specify them next values will be set:
    ```
     POSTGRES_URL=jdbc:postgresql://postgres:5432/mydb
     POSTGRES_USER=postgres
     POSTGRES_PASSWORD=postgres
    ```
    
    JWT_SECRET and INTERNAL_TOKEN are mandatory.
  
    To generate JWT_SECRET use next command: 
    
    Linux:
    
        openssl rand -base64 32
        
    Windows PowerShell: 
        
        [Convert]::ToBase64String((1..32 | ForEach-Object { Get-Random -Minimum 0 -Maximum 256 }))

    INTERNAL_TOKEN can be anything.

 3. Use <code>docker compose up</code> in the root directory to start services
      
      * After startup:
          - auth-api -> http://localhost:8080
          - data-api -> http://localhost:8081
          - postgres -> localhost:5432
       
 4. How to use endpoints? 
  
  * /api/auth/register
    
        curl -X POST http://localhost:8080/api/auth/register -H "Content-Type: application/json" -d '{"email":"a@a.com","password":"pass"}'
  
  * /api/auth/login
  
        curl -X POST http://localhost:8080/api/auth/login -H "Content-Type: application/json" -d '{"email":"a@a.com","password":"pass"}'
    
    (please save the received token after login)
  
  * /api/    process (change <mark>'<your_token_from_login>'</mark> with token returned in previous response): 
  
        curl -X POST http://localhost:8080/api/process -H "Authorization: Bearer <your_token_from_login>" -H "Content-Type: application/json" -d '{"text":"hello"}'
  
   Expected output is a reversed and uppercased text. 
