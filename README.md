# ChatK8S

The Goals of the assignment are to demonstrate:

1) A working knowledge of kubernetes deployments.
2) A understanding of services and networking within kubernetes
3) How to build applications that take advantage of kubernetes features like secrets and configmaps.
4) How to use 12 Factor App methodology to build services that are able to leverage the power of kubernetes.

To complete the assignment you will need to build some portions of a **prototype** application in the language of your group’s choice.  This language may change from portion to portion, as the group sees fit, as this leverages the separation provided by kubernetes and demonstrates that abstraction of services in relation to each other in a container environment.

The application will be a small chat service, which allows multiple clients to chat over websockets, while logging the timestamp, user, and message to a database.  The output logs of all involved services within kubernetes should be transported to a logging solution, which may be any you select.  This includes but is not limited to various pre-packaged logging services, or even a hand-built service that runs alongside the primary service in it’s pod.

All services should provide health checks to kubernetes.

The suggested structure is:
* A log database pod
* A websocket server pod
* A message database pod

The websocket server pod should be able to replicate up to 3 pods, with a minimum of one.

It is highly recommended that you make use of “kubectl -k apply” as a means of deploying multiple files to your k8s instance. For more information: https://kubernetes.io/docs/tasks/manage-kubernetes-objects/kustomization/

Please remember in all cases that this is a **prototype** level application, and while it should conform to 12 factor it need not be robust, or even well written.  The point is purely to exercise kubernetes and it’s usage from a code-first standpoint.

**Why websockets?** Websockets are chosen as they are a well supported technology in many languages, if your language does not have good library support for websockets, then please feel free to use alternative means such as long-polling or forced refreshes for your frontend instead.
