
# Market Simulation Overview: 

This is a program for my assignment in a software architecture course.

The goal is to simulate a stock market with functionality like searching, buying and adding products
as an authorized user

It was an excerise in using Lombok, Junit and Maven as well

It will probably not be updated again, maybe I will implement a GUI and/or fix the monolithic EndUser class, but most likely not... there are better and more fun projects out there


# Features:

- User can search, buy and add products
- User interaction adapts the market
  - buying a product increases the price of the product and vice versa
  - same goes for increasing/decreasing product stock
- Console based menus, with no infinite loops :)
- Testing for the things I could test


# Lessons learned:

- Think about testing from the start
- Avoiding creating too big classes
  - EndUser got way too big in developement. Menus, input validation and user input should've been separated
  - This made testing for EndUser too difficult as well
- Use kanban boards instead of README.MD as a to do list


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
