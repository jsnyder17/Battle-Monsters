import pandas as pd
import requests
from bs4 import BeautifulSoup

page = requests.get('https://bulbapedia.bulbagarden.net/wiki/List_of_moves')

print("Fetching data ... ")

tables = pd.read_html(page.content)

print("Formatting data ... ")

df = pd.DataFrame(tables[1])

del df['#']
del df['Contest']
del df['Gen']

for col in df.columns:
    index = 0
    asteriskIndex = 0
    percentIndex = 0

    while index < df.shape[0]:
        asteriskIndex = df[col][index].find("*")
        percentIndex = df[col][index].find("%")

        if (asteriskIndex != -1 or percentIndex != -1):
            temp = ''
            if (asteriskIndex != -1):
                temp = df[col][index][0:asteriskIndex]
            if (percentIndex != -1):
                temp = df[col][index][0:percentIndex]
            
            df[col][index] = temp

        if (df[col][index] == '—'):
            df[col][index] = -1

        index += 1

print("Writing data ... ")

df.to_csv("movedata.csv", index=False, header=False, sep='|')

print("Done. ")