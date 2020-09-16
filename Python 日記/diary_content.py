import csv
import pandas as pd
import numpy as np
import diary_login


def menu():
    print("-"*60)
    print("{d0:^60s}".format(d0="歡迎使用心情日記系統"))
    print("{d1:^60s}".format(d1="1. 開始寫日記"))
    print("{d2:^60s}".format(d2="2. 歷史紀錄"))
    print("{d3:^60s}".format(d3="3. 查詢日記"))
    print("{d4:^60s}".format(d4="4. 登出系統"))
    print("-"*60)
    
def index(_user):
    choice = str(input(""))
    _user = _user
    
    if choice == "1":
        create_diary(_user)
        
    elif choice == "2":
        history_diary(_user)
        
    elif choice == "3":
        search_diary(_user)
        
    elif choice == "4":
        print("您已登出")
        print("請重新登入")
        diary_login.login()
    else:
        index(_user)
        
    

    
def create_diary(_user):
    global date, weather, mood, content
    date = input("日期 (YYYYMMDD): ")
    weather = input("天氣: ")
    mood = input("心情: ")
    content = input("日記內容: ")
    today_list = [date,weather,mood,content]
    
    time = pd.read_csv('%s.csv' %_user)
    mydata = time.values.tolist()         # 判斷日期有無重複
    
    for i in mydata:
        if str(date) == str(i[0]):
            print(i[0])
            print("這一天已經有寫日記了,請用查閱來進行其他動作")
            menu()
            index(_user)
            
    
    with open('%s.csv' % _user, 'a', newline='', encoding='utf-8') as diary:                # 開啟輸出的 CSV 檔案
        writer= csv.writer(diary) # 建立 CSV 檔寫入器
        writer.writerow([date,weather,mood,content])     
        print("寫入成功")
    menu()
    index(_user)
    
        
    
    
def history_diary(_user):
    time = pd.read_csv('%s.csv' %_user)
    mydata = time.values.tolist()
    print("寫入日期")
    if len(mydata) == 0:
        print("還沒有開始日記")
        menu()
        index(_user)
        
    for i in mydata:
        print("\t %s" %i[0])
    menu()
    index(_user)
    
        
def search_diary(_user):
    global search
    search = str(input("請輸入要查閱的日期"))
    time = pd.read_csv('%s.csv' %_user)
    mydata = time.values.tolist()
    a = 0 
    for i in mydata:
        if search == str(i[0]):
            print(i)
            a = 1
            revise_diary(_user)
    if a == 0:
        print("這天你沒有寫日記喔 !")
    menu()
    index(_user)
            
def revise_diary(_user):
    ans = str(input("你要對此篇日記做什麼? (1)修改 (2)刪除 (3)返回主選單"))
    if ans == "1":
        date1 = (input("請再次輸入你要修改的日期"))  
        date1 = int(date1)        
        time = pd.read_csv('%s.csv' %_user)
        df = pd.DataFrame(time)
        x = df[(df.日期==date1)].index.tolist()  # 抓到指定日期的index
        if len(x) == 0:
            print("這天你沒有寫日記喔 ! 請重新確認日期。")
            return

        newtime = input("請輸入新的日期(或不修改請按enter)")
        newweather = input("請輸入新的天氣(或不修改請按enter)")
        newmind = input("請輸入新的心情(或不修改請按enter)")
        newcontent = input("請輸入新的內容(或不修改請按enter)")
        
        if len(newtime) > 0:
            newtime = newtime
        else: 
            newtime = df.loc[x,"日期"]
        if len(newweather) > 0:
            newweather = newweather
        else:
            newweather = df.loc[x,"天氣"]
        if len(newmind) > 0:
            newmind = newmind
        else:
            newmind = df.loc[x,"心情"]
        if len(newcontent) > 0:
            newcontent = newcontent
        else:
            newcontent = df.loc[x,"日記內容"]
        i = [newtime,newweather,newmind,newcontent]
        df.iloc[x,:] = i
        df.to_csv('%s.csv' %_user, index=False, encoding='utf-8')
        print("修改成功")
        
        menu()
        index(_user)
    
    elif ans == "2":
        doublecheck = str(input("確定要刪除嗎? (1)確定 (2)取消 "))
        if doublecheck == "1":
            time = pd.read_csv('%s.csv' %_user)
            df = pd.DataFrame(time)
            date2 = int(input("請再次輸入你要刪除的日期"))
            y = df[(df.日期==date2)].index.tolist()
            if len(y) == 0:
                print("這天你沒有寫日記喔 ! 請重新確認日期。")
                return
            df.drop(index = y, axis=0, inplace=True)
            df.to_csv('%s.csv' %_user, index=False, encoding='utf-8')
            print("刪除成功")
            print(df)
            
        menu()
        index(_user)
        
        
    elif ans == "3":        
        menu()
        index(_user)
            
 