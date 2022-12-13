# Contributing

## Adding new rules

When adding a new rule, the following steps are required:
- Declare the rule
- Implement a check
- Add the check to the visitor

### Declaring the rule

The new rule must be declared into 2 files :
- `swift-lang/src/resources/ecocode-swift_profile.json` 
- `swift-lang/src/resources/ecocode-swift-rules.json`

### Implementing a check

In order to implement a check for the rule, create a Check class inherited from `RuleCheck` in `src/main/java/io/ecocode/swift/checks`.

Have a look at `swift-lang/src/main/java/io/ecocode/swift/checks/idleness/IdleTimerDisabledCheck` to learn more about the implementation.

### Adding check to the visitor

Once implemented, add the new check to `swift-lang/src/main/java/io/ecocode/swift/EcoCodeSwiftVisitor`.

For example:

```java
    public EcoCodeSwiftVisitor() {
        ...
        // Load checks
        checks.add(new MyNewCheck());
        ...
    }
```
