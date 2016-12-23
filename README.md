# new-jersey

This is a simple example of how to use Jersey to dispatch manually to JAX-RS
annotated classes, outside a servlet container.

This might be valuable in situations where you have some serialized
representation of HTTP calls, but not running within a servlet container, and
you want to dispatch to the JAX-RS annotated classes directly without starting
up a servlet container and taking on the overhead of extra HTTP calls.  Instead
of writing a monolithic controller that needs to handle all possible method
calls, you can reuse the JAX-RS annotations and dispatch manually through Jersey
as the controller.  This also avoids a dual-maintenance problem over time, where
adding new JAX-RS annotated classes to an existing application would require
corresponding code changes in the monolithic controller.

The project is called New Jersey, like the U.S. state, because I am clever.
