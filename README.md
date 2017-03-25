# cloudfoundry-playground

## Build, Deploy, Configure

### build and deploy service broker
```
./gradlew clean build
cf target -o kozlowst -s brokers
cf push
```
  
### Register service broker
```
cf create-space apps // create if not done yet
cf target -o kozlowst -s apps
cf create-service-broker hashmap-kozlowst user user https://hashmap-cf-service-broker.cfapps.io --space-scoped
```
  
### Create instance and bind service insnace
```
cf marketplace -s hashmap-kozlowst
cf create-service hashmap-kozlowst basic hashmap-histogram
cf create-service-key hashmap-histogram hash-hist-001
```

### Check keys
```
cf service-keys hashmap-histogram
$> hash-hist-001
```

### Display key's info
```
cf service-key hashmap-histogram hash-hist-001
```	
	
## ROLLBACK

### remove serivce key
```
cf delete-service-key hashmap-histogram hash-hist-001
```

### remove serivce insnace
```
cf delete-service hashmap-histogram
```

### remove service broker
```
cf delete-service-broker hashmap-kozlowst
```
