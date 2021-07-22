Em chào thầy ạ
Nếu thầy có đọc file này thì sau đây em xin phép được hướng dẫn thầy chạy app lúc đầu
B1: Thầy import Project vào IDE của thầy
B2: Thầy chạy script sql BMTT.sql (thầy chỉ cần nhấn chạy thôi ạ)
B3: Sau đi đã có database thì để chạy được chương trình, thầy run file MainAdmin nằm ở trong thư mục GUI
B4: Thầy nhấn vào nút register admin (tài khoản admin được gán cứng, user: admin@gmail.com, pass: admin)
B5: Để chạy được form người dùng thì thầy tiến hành chạy file MainUser
Đó là các bước đầu để chạy project này.
Nếu có xuất hiện lỗi:
- Lỗi sai tài khoản mysql => thầy vào đường dẫn src/main/resources/hibernate.cfg.xml và sửa lại tên tk và mk db
- Lỗi lúc chạy form admin hoặc user => lỗi này do một số máy không chạy được look and feel
				    => thầy có thể mở MainAdmin dòng số 63 và comment dòng đó lại, 
					tương tự với form MainUser thì ở dòng 43
