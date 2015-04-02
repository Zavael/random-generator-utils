# random-generator-utils
Helper classes to work with random cases

* Random number generator
* Generic empiric random type decider
* Success decider

Abstract class Randomizer has two static methods to get
* random double value between 0.0(inclusive) and 1.0(exclusive)
* random int value between 0 (inclusive) and the specified upper bound value (exclusive)

Class OddsDecider evaluates for you the success of provided odd chance. Internaly it uses Randomizer to get random double and than compares it with provided chance. If random value is less than provided value, return true.
* OddsDecider.decideSuccess(0.6) - 60% chance of getting succes

Class EmpiricDecider evaluates provided options Map<T, Double> optionsWithOdds with their weighted odds. You can provide the chance double value or the second method generates it for you using the Randomizer class. The chance is modified according to the weighted odds.
```
Map<String,Double> options = /* init the map, for example Option1:1.5;Option2:3.0 */
String selectedOption = EmpiricDecider.getSuccessfulOption(options);
/* the generated chance - for example 0.3 - is modified accorging to the odds to value 1.35 and afterwards it is compared to the odds of the options. There is 33,333% chance that the first option is returned, else the second will.  */
```
