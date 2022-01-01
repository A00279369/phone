insert into phone (phone_Id,phone_Name,phone_Model,phone_Number,user_Id)
values ('2a810635-18d0-40ce-ada6-0c0fd1181225','John''s Pixel','ANDROID','+353881234567','93f3ed0a-92bd-4c82-ba0e-c098111cef59');

insert into phone (phone_Id,phone_Name,phone_Model,phone_Number,user_Id)
values ('2a810635-18d0-40ce-ada6-0c0fd1181226','John''s IPhone','IPHONE','+353881234569','93f3ed0a-92bd-4c82-ba0e-c098111cef59');

insert into phone (phone_Id,phone_Name,phone_Model,phone_Number,user_Id)
values ('2a810635-18d0-40ce-ada6-0c0fd1181227','Mary''s IPhone','IPHONE','+353881234570','93f3ed0a-92bd-4c82-ba0e-c098111cef60');

insert into phone (phone_Id,phone_Name,phone_Model,phone_Number,user_Id)
values ('2a810635-18d0-40ce-ada6-0c0fd1181228','Mary''s Pixel','ANDROID','+353881234571','93f3ed0a-92bd-4c82-ba0e-c098111cef60');



insert into user (user_Id,user_Name,password,email_Address,preferred_Phone_Number)
values ('93f3ed0a-92bd-4c82-ba0e-c098111cef59','john_user',HASH('SHA256', 'PASSWORD',1000),'john@example.com','+353881234567');

insert into user (user_Id,user_Name,password,email_Address,preferred_Phone_Number)
values ('93f3ed0a-92bd-4c82-ba0e-c098111cef60','mary_user',HASH('SHA256', 'PASSWORD',1000),'mary@example.com','+353881234570');



