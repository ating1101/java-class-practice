import csv
import pandas as pd
import numpy as np

def write_csv(points):

    with open('type_record.csv', 'a', newline = '') as f:
        name = input("請輸入玩家名稱 : ")
        writer= csv.writer(f)
        data_row = ['%s' %name, '%s' %points]
        writer.writerow(data_row)

def read_csv():

    record=pd.read_csv('type_record.csv', index_col="玩家")
    record[['分數']] = record[['分數']].apply(pd.to_numeric)
    record = record.sort_values('分數', ascending=False)
    record.reset_index(inplace=True)
    record.index = [i for i in range(1, len(record)+1)]
    print(record.head())

def _del():
    name = input("請輸入要刪除的玩家")
    record=pd.read_csv('type_record.csv')
    mydata = record.values.tolist() #整個轉為list
    new = []
    for i in range(len(mydata)):
        if mydata[i][0] != name:
            new.append(mydata[i])
    store = pd.DataFrame(columns=["玩家","分數"],data=new)

    store.to_csv("type_record.csv", encoding = 'utf-8',index = False)   
 