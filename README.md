# "Integrated Tests Are A Scam" - an example

This is an implementation of the controller described as example by J.B. Rainsberger in his talk ["Integrated Tests Are A Scam"](https://vimeo.com/80533536). Its job is to produce a report of how many customers signed up in the last seven days.

The goal of this example is to show the difference between integrated tests, that lead to a combinatorial explosions of tests, and the collaboration/contract tests as proposed by J.B.

`CombinatorialExplosionUsersRegisteredInLastSevenDaysControllerTest` shows a small portion of the integrated tests you would need to write in order to test all the available combinations between the client (the controller) and the server (its collaborator `InMemoryUsersRepository`).

On the client we need to know what to do if the server returns the following values as a a list of customers:

* zero customers
* one customers
* a few customers
* many customers (2000)
* oops! (an error, something went wrong)

Five tests total.

Let's consider the first test. We want the controller to invoke a "No customers found" page if the server returns zero customers.
But there are at least two conditions that make the server return zero customers: no customers registered at all, a few customers registered but none from date X.
With the integrated tests we have to consider these two conditions to make sure the controller properly invoke the "No customers found" page. Instead of one test, two tests.
The same problem occurs with the other test scenarios (one customer found, a few customers found...).

`CollaborationUsersRegisteredInLastSevenDaysControllerTest` shows how to test the same controller, using collaboration tests.
For the "zero customers" case, we have only one test. With a collaboration test, we don't need to know at which conditions the server is going to return zero customers. We pretend the server return zero customers, no matter how, and we focus on testing the controller behaviour.