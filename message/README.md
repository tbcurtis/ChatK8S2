# message 

## Overview

Kubernetes service to save and deliver messages from a postgres database

## Setup postgres database

```
kubectl apply -f postgres-secrets.yml
kubectl apply -f pv-volume.yml
kubectl apply -f pv-claim.yml
kubectl apply -f postgres-service.yml
kubectl apply -f postgres-deployment.yml
```


## Start postgres command prompt

```
kubectl get pods
export POD_NAME=`kubectl get pods | grep post | awk '{ printf $1 }'`
winpty kubectl exec -it ${POD_NAME} -- psql -U postgres
```

## Create table

```
DROP TABLE user_messages;
CREATE TABLE user_messages (dte TIMESTAMP PRIMARY KEY NOT NULL, usr TEXT NOT NULL, msg TEXT NOT NULL);
```

Verify table was created by running:
```
\d
```

