import pandas as pd
import csv
import diary_content




def login():
    choice = input("請選擇要進行的操作(1)登入系統 (2)建立帳號 (0)離開系統 : ")
    if choice == "1":
        return 1
        
    elif choice == "2":
        return 2
        
    elif choice == "0":
        return 0
        
    else:
        print("請選擇正確的操作")
        login()
        
        
def _login():
    global _user
    _user = input("請輸入帳號或輸入 exit 回到主選單 :")
    if _user == "exit":
        login()
    _passwd = str(input("請輸入密碼"))

    userlist=pd.read_csv('userlist.csv') # 讀取帳號密碼csv檔
    _userlist = userlist.values.tolist() # 轉成list



    for i in range(len(_userlist)):   # 帳密比對
        if _userlist[i][0] == _user:
            if str(_userlist[i][1]) == _passwd:
                print("登入成功")
                diary_content.menu() # 登入畫面&開始函數
                diary_content.index(_user)

            else:
                print("帳號或密碼錯誤,請重新登入")
                _login()
    print("帳號錯誤,請重新登入")
    _login()
        
def createaccount():
    _user = input("請輸入帳號 :")
    _passwd = str(input("請輸入密碼"))
    check = str(input("請再次輸入密碼"))
    if _passwd != check:
        print("兩次輸入密碼不同,請重新填寫")
        createaccount()
        
    userlist=pd.read_csv('userlist.csv') # 讀取帳號密碼csv檔
    _userlist = userlist.values.tolist() # 轉成list
    
    
    for i in range(len(_userlist)):   # 帳號重複比對
        if _userlist[i][0] == _user:
            print("此帳號已經被使用,請重新填寫")
            createaccount()
            
    with open('userlist.csv', 'a') as f:  # 新增帳號資料
        writer= csv.writer(f)
        data_row = ['%s' %_user, '%s' %_passwd]
        writer.writerow(data_row)
        print("創建成功,請重新登入")
        
    with open('%s.csv' % _user, 'a', newline='', encoding='utf-8') as diary:
        writer= csv.writer(diary)
        writer.writerow(["日期","天氣","心情","日記內容"])
    return
        
        
