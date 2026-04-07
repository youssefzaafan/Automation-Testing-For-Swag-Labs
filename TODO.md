# Fix "unknown locator" logging issues

## Current Status
- ✅ Root causes identified (RelativeLocator toString() + SLF4J misuse)
- ✅ Debug confirmed via logs

## Tasks
- [ ] 1. Fix ElementActions.getTextFromInput() SLF4J logging
- [ ] 2. Improve HomePage RelativeLocator logging  
- [ ] 3. Verify with test run (`mvn test`)

## Files to Edit
- `src/main/java/com/swaglabs/utils/ElementActions.java`
- `src/main/java/com/swaglabs/pages/HomePage.java`