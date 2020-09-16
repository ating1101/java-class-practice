#!/usr/bin/env python
# coding: utf-8

# In[ ]:


def show_menu():
    print("-" * 60)
    print("{d1:^60s}".format(d1="1. 開始遊戲"))
    print("{d2:^60s}".format(d2="2. 查看排行榜"))
    print("{d3:^60s}".format(d3="3. 刪除玩家"))
    print("{d4:^60s}".format(d4="4. 離開遊戲"))
    print("-" * 60)

    
def level():
    import time, random, string, threading
    points = 0
    bulletin_board = []

    def level_start():
        global a
        a = 60
        
        while a != 0: #計時器
            bulletin_board.extend(random.choice(string.ascii_letters) for x in range(1, 2)) #產生字母
            print("還剩下 %d 秒 : " %(a) , bulletin_board)
            a -= 1
            time.sleep(1)  #延遲秒數


    t = threading.Thread(target = level_start)
    
    t.start()
    
    while a != 0:
        _input = input("")        
        if _input == bulletin_board[0]:
            del bulletin_board[0]
            points += 1
    else:
        print("你得到了 %d 分" %(points))
      
    t.join()
    
    return points
