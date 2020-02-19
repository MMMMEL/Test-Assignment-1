# Grading for assignment-1-litchi
**Class:** 2020SPCS5500SV<br>
**Date:** 2020-02-03<br>
**Grader:** TA NR

## Total: 89/100
## Comments
* (-20) JavaDoc - missing all generated documentation; missing @throw in your file documentation also.
    - '<strong>Generate JavaDoc for all public, package, protected, and private members of both classes into a "doc" subdirectory.</strong>'

REGRADE:
* (+10) - JavaDoc now included.
* (-1) - getLineCount() completely ignores the instance URL, using a hard-coded text URL instead.  This is incorrect.

NOTES: 
* using assertTrue() in many (most) cases rather than using assertEquals() means that you get no useful information about the expected and actual values if the test fails. Try using assertEquals() in the future.
* Your tests never try to access a non-existent URL on the server. If you added those tests, you would discover the ContentInfo functions return wrong answers rather than throwing an IOException because the 404 response returned by HttpUrlConnection.connect() also returns an HTML page. So instead of reporting an IOException, ContentInfo functions all report on the HTML page it returns. 