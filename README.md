# The triangles app
This application classifies triangles based on their side length

# Bulding
Execute `./gradlew[.bat]`

That will create a jar with all the code and dependencies ready to be executed in ` ./build/libs/triangles-app-1.0.0.jar`

# Executing

`java -jar triangles-app-1.0.0.jar --sides <side 1 length> <side 2 length> <side 3 length>`

## Example
```
#java -jar triangles-app-1.0.0.jar --sides 1 2 3
Triangle type: Scalene
```
```
#java -jar triangles-app-1.0.0.jar --sides 1 2 1
Triangle type: Isosceles
```
```
#java -jar triangles-app-1.0.0.jar --sides 1 1 1
Triangle type: Equilateral
```
```
#java -jar triangles-app-1.0.0.jar --sides 0.25 0.25 1
The triangle can not exist
```