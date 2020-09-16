import diary_content
import diary_login

while True:
    ans = diary_login.login()
    if ans == 0:
        print("感謝您使用本系統 Bye Bye")
        break
    
    elif ans == 1:
        diary_login._login()
    
    elif ans == 2:
        diary_login.createaccount()
