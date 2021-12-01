*Requirements* 
# admin user can create Notification. 
# admin user can preview a Notification. 
# admin user can list existing Notification and check their view status on a user by user basis. 
# admin user can cancel or modify a notification. 
# A user can view a notification only once or resolve only once. Currently we notify users using email on each microservice.(nexusdmp, integration, kwt). This system should extend this capability beyond specific delivery method. Current email notification usage should be refactored in a separate micro service which implement email delivery as an option. This micro service can be refactored to interface to some platform if necessary later on. 
*API*
 /notify params: 
 # target - list of client ids of targeted clients or empty list for all clients. 
 # target user id list. 
 # action_required - boolean flag 
 # title - string 
 # body - html string 
 # button_type - enum of predefined button types “OK“, “CLOSE“, “ACCEPT“, “CANCEL“ 
 # Start date - date time after which the notification should be shown 
 # End date - data time after which the notification should not be shown. 
 # theme - enum of predefined themes : alert, warning, info. 
 # email template (string) 
 h4. /list-notification params: 
 # datetime _from 
 # datetime_till 
 returns list of notifications ordered by datetime _from. 
 h4. /get-notification-views param: notification_id return list of users who viewed the notification. 
 h4. /get-notifications params: user_id. returns list of targeted notification. 
 h4. /mark-notification-as-shown params: user_id, notification_id. This endpoint should change view state of a notification. FE should call if if a user opened and viewed the notification. 
 h4. /perform-notification-action params: user_id, notification_id. Applied only for notifications that require an action. 
 h3. Entities 
 h4. Notification |PK|id|int| | |title|string(255)| | |body|blob| | |button_type|string(30)| | |show_from|timestamp| | |show_till|timestamp| | |theme|string(30)| | |action_done|int(1)| | |action_required|int(1)| 
 h4. NotificationTarget |FK|notification_id| |FK|client_id| 
 h4. NotificationView |FK|notification_id| |FK|user_id| 
 h4. Complexity Estimation Querying of notifications:
  {code:sql}select * from notification as n inner join notification_target as nt on n.id = nt.notification_id inner join jhi_user_client as uc on nt.client_id = uc.client_id where uc.user_id = &user_id and not notification_id in (select notification_id from notification_view where user_id = &user_id) {code} 
  3 joins → n log(nt) log(u)*log(uc) nw This is not most efficient way if . With current usage assumptions it’s close to O(1) in case of using im mem cache. h4.