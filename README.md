# Introduction

## How to compile

```
sbt clean test assembly
```

## How to run

### Locally
```
spark-submit --class "com.swalfie.app.MainApp" --master local[4] ./target/scala-2.12/spark-example-assembly-0.1.0-SNAPSHOT.jar 
```
