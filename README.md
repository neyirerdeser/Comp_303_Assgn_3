received a grade of 77% along with Assignment4

## Problem Statement

Using the principles, mechanisms, and techniques seen in Chapter 4 of the book, design and write the code necessary to enhance the movie library design from the baseline code (adapted from solutions to Assignment 2) to meet the following requirements. *Your solution should continue to respect principles of good design seen in prior chapters.*

1. Allow only one Library object to ever be created in your application. Add two fields -- a Name and an EmailID -- to the library, both of which can be changed by the client. The Library must always have a Name, but the EmailID may be missing. Ensure you implement appropriate getters and setters. [5 points]
2. For any given title, there can only ever be one Movie object with that title in your application. Implement this constraint for TV Shows as well. Hint: you should not throw exceptions when the title already exists in the library. [5 points]
3. Implement an equality criteria for watchlists based on their content. Two watchlists are considered duplicates if they aggregate the same Watchable objects in the same order, regardless of the name of the watchlists. [5 points]
