#!/bin/bash

csvFile="french-verb-conjugation.csv"
xmlFile="french-verbs.xml"

awk -F, 'BEGIN {
    FS=",";
    print "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
    print "<verbs>";
}

NR > 1 {
    print "    <verb>";
    print "        <infinitive>" $1 "</infinitive>";
    print "        <firstSingular>" $6 "</firstSingular>";
    print "        <secondPlural>" $10 "</secondPlural>";
    print "    </verb>";
}
END {
    print "</verbs>";
}' "$csvFile" > "$xmlFile"
