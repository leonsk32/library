import * as firebase from 'firebase/app';
import 'firebase/auth';

const config = {
  apiKey: 'AIzaSyBU71DQsVUEr3n6qLEaTGTapjmrwQqOspE',
  authDomain: 'adc-library.firebaseapp.com',
  databaseURL: 'https://adc-library.firebaseio.com',
  projectId: 'adc-library',
  storageBucket: 'adc-library.appspot.com',
  messagingSenderId: '1022187720844',
  appId: '1:1022187720844:web:7c28e738a6fda5c0aaaba9',
  measurementId: 'G-YP3QHQ9B9Z',
};

export default {
  init(): void {
    firebase.initializeApp(config);
    firebase
      .auth()
      .setPersistence(
        process.env.NODE_ENV === 'test'
          ? firebase.auth.Auth.Persistence.NONE
          : firebase.auth.Auth.Persistence.SESSION,
      );
  },
  login(): Promise<firebase.auth.UserCredential> {
    const provider = new firebase.auth.GoogleAuthProvider();
    return firebase.auth().signInWithPopup(provider);
  },
  logout(): any {
    return firebase.auth().signOut();
  },
};
