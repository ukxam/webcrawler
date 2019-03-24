# webcrawler
webcrawler on static internet files...

a web crawler is a software that requests pages from the
Internet, parses the content to extract all the links in the page, and visits the
links to crawl those pages, to an infinite depth.

Expected output for internet_1.json
Success:
[
"http://foo.bar.com/p1","http://foo.bar.com/p2",
"http://foo.bar.com/p4","http://foo.bar.com/p5",
"http://foo.bar.com/p6"
]
Skipped:
[
"http://foo.bar.com/p1","http://foo.bar.com/p2",
"http://foo.bar.com/p4","http://foo.bar.com/p5"
]
Error:
[
"http://foo.bar.com/p3","http://foo.bar.com/p7"
]
Expected output: for internet_2.json
Success:
[
"http://foo.bar.com/p1","http://foo.bar.com/p2",
"http://foo.bar.com/p3","http://foo.bar.com/p4",
"http://foo.bar.com/p5"
]
Skipped:
[
"http://foo.bar.com/p1"
]
Error:
[]
Expected output: for internet_3.json
Success:
[
"http://foo.bar.com/p1"
]
Skipped:
[]
Error:
[
"http://foo.bar.com/p999999999"
]
