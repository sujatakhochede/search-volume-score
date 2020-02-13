# Sellics Tech Assignment

Implemented a microservice with a single REST endpoint.This will receive a single keyword as an input and return a score for that same exact keyword.
Score ranges from 0(lowest) - 100(highest)

REQUEST GET :-  http://localhost:8080/estimate?keyword=iphone+charger

RESPONSE: -
{
   "keyword": "iphone+charger",
   "score": 100
}

## Q and A
### Q-What assumptions did you make?

#### A-Assumptions on Amazone autocomplete API after reverse-engineering on Amazon:
1. It returns 10 suggestions for the text/keyword entered in a search-box, these items are the top used keywords by other customers who also performs searching
2. Suggestions are always prefixed with the entered keyword
3. It doesn't say anything on the score or search volume as they might be computing this list based on several business strategies. We may say these are 10 suggestions important for Amazone right now.
4. It returns relative item suggestions if exact match not available(e.g. in case of keyword incorrectly spelled), Search strategy might be different for exact match and relative match
5. Its ordered by score and not alphabetically
6. Service creates a list of substrings for specified keywords starting from the 1st letter. If keyword is 'samsung' in result we receive list with 6 substrings - [s, sa, sam, ...]
7. Service creates list of request commands and performs requests to Amazon completion API, so for 'samsung' it prepares 7 requests and gets 7 responses.
8. Keyword entered might be a substring of suggestion keywords. e.g. If keyword is 'lego', suggestions might be 'legos for boys'. So, I won't give score as keyword found is 'legos' and not 'lego'.
    
#### B- Assumptions on the assignment:
1. Have to implement a micro service with a single endpoint which returns search-volume score for a particular keyword at this time.
2. Have to compute a search-volume score for a particular keyword based on the suggestions provided by amazone api.
3. Search-volume score would tell how much my keyword is popular at this moment.
4. Score will always range from 0 to 100.
5. Have to look for exact prefix match for whole keyword.
6. Keyword which is substring of keywords available in suggestion doesn't qualify for score.
7. User may enter Keyword in any case, with special characters and with white spaces - Program should handle all of these
8. Service has SLA of 10 seconds meaning it should not take more than 10 seconds.

### Q. How does your algorithm work?
1. Receives keyword as a request parameter (e.g. Samsung charger).
2. Retrieves suggestions for the keyword from Amazone autocomplete API(https://completion.amazon.com/search/complete?search-alias=aps&mkt=1&q=iPhone+charger).
3. Looks for a keyword in each suggestion received from amazone API as below:
      i. exact match found in suggestion, e.g. 'samsung charger'
      ii. exact prefix match with a suggestion, e.g. 'samsung charger cable 3.6ft'
      iii. Not considering relative match as its score for a keyword so incorrectly spelled keyword is not my hottest keyword(provided exact match not found in suggestions).
      e.g. If keyword entered is 'somsung', amazone api still gives me 10 suggestions smartly by considering it as a typo. so score for the keyword 'somsung' is 0 if not in suggestions.
4. Generate number of matches found for suggestions as above
5. Score is calculated by multiplying number of matches with 10. e.g. if number of matches found are 7, then score is 70.

### Q. Do you think the (*hint) that we gave you earlier is correct and if so - why?
 Yes, Amazone as a biggest and popular online shopping portal, must be using solid strategy for their product discovery and the search  volume score is affected by their business strategy.So, such order becomes comparatively insignificant here.

### Q. How precise do you think your outcome is and why?
 My solution is quite precise and simple as I've used a simple math for calculating a score as per assumption made in point a.I'm saying my keyword is popular/hottest based on the suggestions and not the actual trend in amazon.

Solution would definitely need extension in case of below matters:
1. How correctly suggestions are getting generated
2. Suggestions might not be that popular but actually purchased more in terms of order completion. e.g. 'Jeans' gives me 10 suggestions which is too much popular keyword, and 'sujata' also gives 10 suggestions which I think comparatively not popular among amazone products.so it quite difficult to say on whether its actually hottest keyword
3. SLA - says my service should not take more than 10 seconds, so used hystrix command to timeout if takes more time.
    i. I gave a thought to call amazone API multiple times during this SLA and give the latest score or the average, but then Its not making any sense because User would not want to wait for 10 seconds and get latest result.Instead I'll provide response as soon as I'm done calculating because user want to know the score of his/her keyword at that moment.
    ii. secondly, we should consider the time taken for retrieving amazone api response and calculating score.
4. In case amazone returns more than 10 suggestions and score may cross 100.(In my solution I've taken care so my score will not cross 100)

```
________________________________________________________
How to run project?

    Build project with command:
    mvn clean install

    Run project with command:
    mvn spring-boot:run

    To run project in IDE:
    1.import project in IDE as maven project
    2.Run SearchVolumeApplication class

________________________________________________________
JavaDoc can be found at:
/search-volume-master-sujata/JavaDoc/index.html
