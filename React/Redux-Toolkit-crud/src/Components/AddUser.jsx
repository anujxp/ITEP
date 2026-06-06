import React from 'react'

const AddUser = () => {

  return (
    <>
      <div className='mt-16'>
        
<form class="max-w-sm mx-auto bg-grey-500">
  <div class="mb-5">

    <input type="text" id="name" class="bg-neutral-secondary-medium border border-default-medium text-heading text-sm rounded-base focus:ring-brand focus:border-brand block w-full px-3 py-2.5 shadow-xs placeholder:text-body" placeholder="name@flowbite.com" required />
  </div>
  <div class="mb-5">
    <input type="text" id="name" class="bg-neutral-secondary-medium border border-default-medium text-heading text-sm rounded-base focus:ring-brand focus:border-brand block w-full px-3 py-2.5 shadow-xs placeholder:text-body" placeholder="name@flowbite.com" required />
  </div>
 
  <button type="submit" class="text-white bg-brand box-border border border-transparent hover:bg-brand-strong focus:ring-4 focus:ring-brand-medium shadow-xs font-medium leading-5 rounded-base text-sm px-4 py-2.5 focus:outline-none">Add User</button>
</form>

      </div>
    </>
  )
}

export default AddUser
