import type_tools
import type_record

while True:
    type_tools.show_menu()
    decision = int(input("請選擇要進行的動作 : "))
    
    if decision == 1:
        points = type_tools.level() #抓分數
        print(points)
        type_record.write_csv(points)
        type_record.read_csv()
        
    elif decision == 2:
        type_record.read_csv() #查詢排行榜
        
    elif decision == 3:
        type_record._del() #刪除玩家
        
    elif decision == 4:
        print("Bye Bye ~ 下次再來挑戰") #離開
        break
        
    else:
        print("請輸入正確的選項")
        continue