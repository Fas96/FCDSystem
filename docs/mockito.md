

# Class under test
# ----------------
# Method under test
## What are test doubles?
 A test double is an object that stands in for another object during testing. Test doubles are used to isolate the code under test from the rest of the system. They can be used to simulate the behavior of real objects, to isolate the code under test from the rest of the system, or to verify that the code under test interacts with other objects as expected.
 The different types of test doubles are:
    - Dummy objects are passed around but never actually used. Usually they are just used to fill parameter lists.
    - Fake objects actually have working implementations, but usually take some shortcut which makes them not suitable for production (an in memory database is a good example).
    - Stubs provide canned answers to calls made during the test, usually not responding at all to anything outside what's programmed in for the test.
    - Mocks are pre-programmed with expectations which form a specification of the calls they are expected to receive. They can throw an exception if they receive a call they don't expect and are checked during verification to ensure they got all the calls they were expecting.
    - Spy objects are stubs that also record some information based on how they were called. One form of this might be an email service that records how many messages it was sent.