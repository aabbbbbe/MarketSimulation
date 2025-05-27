This is a repo for my assignment in a software architecture course.

The goal is to simulate a stock market with functionality like searching, buying and adding products
as an authorized user

# TODO:

- Main Method
  - Testing needs to be implemented
- EndUser class
    - Testing may need to be implemented
- Market class
  - Unit tests need to be implemented
- Product class
  - Testing may need to be implemented
- Testing for the appropriate things

# DONE:

- Needed Dependencies are configured
- NotAuthorizedException
- General layout for program
- add()
- search()
- Create products, so that the market isn't empty in the beginning
- create needed objects
- Create logic for menus (still needed for search and buy)
- Create needed wrapper functions
- Plan log in function for authorized user 
  -  Employee class may be the smarter move? (not the move) (Scrapped)
- buy()
- Implement history for last three intervals
- Interval being when an action was taken
- employee increaseStock()
- Create logic for market
- priceAdjust() logic
  - Should react to market changes
  - Maybe some randomness is appropriate