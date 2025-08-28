import { expect, test } from 'vitest'
import { sum, subtract } from './sum.js'
//need to install vitest first
//npm install -D vitest

test('expectation: adds 1 + 2 to give 3', () => {
  expect(sum(1, 2)).toBe(3)
})

test ('subtract 3 and 1 to get 2', () => {
    expect(subtract(3,1)).toBe(2)
})

/**
 * Testing with Vitest
 * 1. Install various libraries related to vite and jest
 * 2. Prepare the component or the function - as applicable
 * 3. Create a separate file with the extension <component>.test.js (usuallyl written inside the src/test folder)
 * 4. Write the test cases with test() function
 * 5. Mention the expected output using expect() function
 * 6. Run the test using - npm run test
 */