from flask import Flask
from twilio.rest import Client
from flask import request

app = Flask(__name__)
app.config['DEBUG'] = True

@app.route('/AndroidVolley', methods=[ 'GET' , 'POST'])
def display():
         personId = request.form['OTP']
	 # Your Account SID from twilio.com/console
         account_sid = "XXXXXXXXXXXXXX"
         # Your Auth Token from twilio.com/console
         auth_token  = "XXXXXXXXXXXXXX"

         client = Client(account_sid, auth_token)

         message = client.messages.create(
         to="Senders number", 
         from_="twilio assigned number",
         body="Hi. Your OTP is: "+personId)
	
         return "Msg sent"
	
        


if __name__=='__main__':
    app.run(host='0.0.0.0') 