# Read Me First
###What is generator project used for
The generator uses reactive spring (webflux) to generate random numbers between 0 and 200. 
I choose this range, because there are 25% of prime numbers in this range.
There is 200 mils delay in delivering numbers from the end point

    GET: /generate
Numbers are stored in a csv file.
###How to run the generator
### Maven package
run command

    mvn clean package

### Docker

in the project folder run command

     docker build --tag=producer:latest .        

after success run command

    docker run -p8080:8080 producer:latest
