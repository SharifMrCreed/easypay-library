# Easypay-library  for requesting payments and payouts from Mtn mobile money and airtel money in Uganda.

## Step One: Add the jitpack repository to the project level build.gradle file

<pre><code>allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
</code></pre>

## Step Two: Add the dependency below to your app level build.gradle file

<pre><code>dependencies {
	        implementation 'com.github.SharifMrCreed:easypay-library:1.0.4'
	}
</code></pre>

## Step three: in your MakePayments activity, add the following code snippet and start making payments

<pre><code>new EasyPayApiConnection().amount(amount)
                .clientId(clientId)
                .secret(secret)
                .reason(reason)
                .orderId(orderId)
                .phoneNumber(phoneNumber)
                .startPayment();
</code></pre>
