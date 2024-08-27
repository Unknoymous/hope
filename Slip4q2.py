weather=['sunny','sunny','overcast','rainy','rainy','overcast','sunny','sunny',
         'rainy','sunny','overcast','overcast','rainy']
# Define the temperature
temperature=['hot','hot','hot','mild','cool','cool','cool','mild','cool',
         'mild','mild','mild','hot','mild']
# Define the play
play=['no','no','yes','yes','yes','no','yes','no','yes','yes','yes','yes','yes','no']
from sklearn import preprocessing
# Use LabelEncoder to convert the categorical variables into numerical variables
le = preprocessing.LabelEncoder()
#convert string in no
weather_encoded=le.fit_transform(weather)
print (weather_encoded)
#convert string in no
tem_encoded=le.fit_transform(temperature)
label=le.fit_transform(play)
print("temp",tem_encoded)
print("play",label)
#combine
features= (zip(weather_encoded,tem_encoded))
print (features)

from sklearn.naive_bayes import GaussianNB
model= GaussianNB()
model.fit(features,label)
predicted=model.predict([0,2])
print('predicted val',predicted)

