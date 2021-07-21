import pandas as pd
import requests
from bs4 import BeautifulSoup

page = requests.get('https://pokemondb.net/pokedex/all')

print("Fetching data ... ")

tables = pd.read_html(page.content)

print("Writing data ... ")

for x in tables:
    df = pd.DataFrame(x)
    del df['#']
    del df['Total']
    df.to_csv('moredata.csv', index=False, header=False, sep='|')

print ("Done. ")