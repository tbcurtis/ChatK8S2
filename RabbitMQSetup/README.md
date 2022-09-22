# RabbitMQ Setup

## Install the RabbitMQ Cluster Operator

```
$ kubectl apply -f "https://github.com/rabbitmq/cluster-operator/releases/latest/download/cluster-operator.yml"
```

## Deploy Rabbit MQ with your app specific settings

```
$ kubectl apply -f rabbitmq.yaml
$ kubectl apply -f allow-rabbitmq-traffic.yaml
```

### quick start guide 

Open web: https://www.rabbitmq.com/kubernetes/operator/quickstart-operator.html

