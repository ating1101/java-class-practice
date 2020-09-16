#!/usr/bin/env python
# coding: utf-8



import requests
import json
from bs4 import BeautifulSoup as bs
res = requests.get('https://technews.tw/')
soup = bs(res.text,'lxml')


select = soup.select('.block2014')


content1 = []
for i,content in enumerate(select):
    a = {}
    spotlist = []
    a['category'] = soup.find_all('div','cat01')[i].text.strip()
    a['sum_title'] = soup.find_all('div','sum_title')[i].text.strip()
    a['sum_title_url'] = 'http:'+content.find('a').get('href')
    for j in range(3):
        b = {}
        b['title'] = content.find_all('li','spotlist')[j].text.strip()
        b['url'] = 'http:'+content.find_all('li','spotlist')[j].a.get('href')
        spotlist.append(b)
    a['spotlist'] = spotlist
    content1.append(a)



file_name = 'practice1.json'
with open(file_name, 'w', encoding='utf8') as file_object:
    json.dump(content1,file_object,ensure_ascii=False)


file_name = 'practice1.json'
with open(file_name, 'r', encoding='utf8') as file:
    data = json.loads(file.read())


for i in data:
    name = 'sum_'+ i['category'] + '_' + i['sum_title'][0:4] + '.txt'
    res = requests.get(i['sum_title_url'])
    soup = bs(res.text, 'lxml')
    
    for i in soup.select('.indent>p'):
        b = i.text.strip()
        with open(name, 'a', encoding='utf8') as file_object:
            file_object.write(b)
  
for i in data:
    c = i['category']
    s = i['spotlist']
    for j in range(3):
        name = 'sum_' + c + '_' + s[j]['title'][0:4] + '.txt'
        res = requests.get(s[j]['url'])
        soup = bs(res.text, 'lxml')    
        for k in soup.select('.indent>p'):
            b = k.text.strip()
            with open(name, 'a', encoding='utf8') as file_object:
                file_object.write(b)

name = []
url = []
for i in data:
    for j in range(3):
        x = i['category'] + '_' + i['spotlist'][j]['title'][0:4]
        y = i['spotlist'][j]['url']
        name.append(x)
        url.append(y)
for i,title in enumerate(name):
    title = 'sum_' + title + '.txt'
    res = requests.get(url[i])
    soup = bs(res.text, 'lxml')
    select = soup.select('.indent p')
    for j in select:
        with open(name[i], 'a', encoding='utf8') as file_object:
            file_object.write(j.text.strip())

