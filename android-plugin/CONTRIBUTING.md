Contributing Guidelines
=======================

Contributions are welcome!

**Before spending lots of time on something, ask for feedback on your idea first.**
Contact us at <contact@ecocode.io> to be sure that we are interested with your contribution idea.

To avoid frustration, please discuss before submitting any contributions.

General Coding recommendations
----------------------

### Make your changes minimal

The less code is modified, the easier to review, and it makes your contribution more likely to be accepted.
This means that your commits should be atomic and have a single purpose. Formatting modifications should not clutter
your changes in order to make the reviewer's job easier.
  
### Test, test, and test

Coding is the easy part. If you want your contribution to be accepted, demonstrate it solves an issue by providing the unit test it solves (that would have failed before).

### Clean commit history

To ease the review, please have a clean, minimal history of commits in your pull request. Your commits should have a single purpose.
This will help to make your contribution accepted as we like to keep a clean linear history and prefer rebase over merging commits.

Plugin-specific recommendations
----------------------

Before submitting an Android-specific SonarQube custom rule, please take a look at our [naming conventions](https://doc.rules.ecocode.io/#how-to-specify-rules). Idealy, take also a look at the [helpers fonctions](./src/main/java/io/ecocode/java/checks/helpers) we wrote to avoid doing things the hard way.
